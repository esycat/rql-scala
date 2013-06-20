package com.esyfur.rql

import scala.collection.JavaConverters._

import com.rethinkdb.{Ql2 => p}
import p.Datum.DatumType.{R_NULL, R_BOOL, R_NUM, R_STR, R_ARRAY, R_OBJECT}

object Datum {

    def apply[T](value: T): Datum[Any] = value match {
        case null       => new NullDatum
        case v: Boolean => new BoolDatum(v)
        case v: Int     => new NumDatum(v)
        case v: Long    => new NumDatum(v)
        case v: Float   => new NumDatum(v)
        case v: Double  => new NumDatum(v)
        case v: String  => new StrDatum(v)
        case v: Array[AnyVal] => ??? // new ArrayDatum(v)
        case v: Array[AnyRef] => ??? // new ObjectDatum(v)
        case _          => {
            val message = "Cannot convert %s to datum.".format(value.getClass)
            throw new RqlDriverError(message)
        }
    }

    def unwrap[T](datum: p.Datum): Any = datum.getType match {
        case R_NULL   => null
        case R_BOOL   => datum.getRBool.asInstanceOf[T]
        case R_NUM    => datum.getRNum.asInstanceOf[T]
        case R_STR    => datum.getRStr.asInstanceOf[T]
        case R_ARRAY  => ArrayDatum.unwrap(datum.getRArrayList.asScala)
        case R_OBJECT => ??? // new ObjectDatum(datum.getRObjectList).asInstanceOf[T]
        case datumType => {
            val message = "Unexpected datum type %s.".format(datumType)
            throw new RqlDriverError(message)
        }
    }

}

object ArrayDatum {

    def unwrap(datum: Seq[p.Datum]): Any

}

private[rql] abstract class Datum[+T] extends Query {

    val termType = p.Term.TermType.DATUM
    val datumType: p.Datum.DatumType

    val value: T

}

final class NullDatum extends Datum[Nothing] {

    val datumType = R_NULL
    val value = null

}

final class BoolDatum(val value: Boolean) extends Datum[Boolean] {

    val datumType = R_BOOL

}

final class NumDatum(val value: Double) extends Datum[Double] {

    //def this(value: Int)   = this(value.toDouble)
    //def this(value: Long)  = this(value.toDouble)
    //def this(value: Float) = this(value.toDouble)

    val datumType = R_NUM

}

final class StrDatum(val value: String) extends Datum[String] {

    val datumType = R_STR

}

final class ArrayDatum[T <: Datum](val value: List[T]) extends Datum[List[T]] {

    val datumType = R_ARRAY

}

final class ObjectDatum[T >: Datum](val value: List[T]) extends Datum[List[T]] {

    val datumType = R_OBJECT

}
