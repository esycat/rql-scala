package com.esyfur.rql

import scala.collection.JavaConverters._

import com.rethinkdb.{Ql2 => p}
import p.Datum.DatumType.{R_NULL, R_BOOL, R_NUM, R_STR, R_ARRAY, R_OBJECT}

object Datum {

    def apply(value: Any): Datum[Any] = value match {
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
        case R_NULL    => null
        case R_BOOL    => datum.getRBool.asInstanceOf[T]
        case R_NUM     => datum.getRNum.asInstanceOf[T]
        case R_STR     => datum.getRStr.asInstanceOf[T]
        case R_ARRAY   => ArrayDatum.unwrap(datum.getRArrayList.asScala)
        case R_OBJECT  => ??? // ObjectDatum.unwrap(datum.getRObjectList.asScala)
        case datumType => {
            val message = "Unexpected datum type %s.".format(datumType)
            throw new RqlDriverError(message)
        }
    }

}

private[rql] abstract class Datum[+T] extends Term {

    protected val termType = p.Term.TermType.DATUM
    protected val datumType: p.Datum.DatumType

    protected val value: T

    protected override def getTermBuilder() = super.getTermBuilder().setDatum(getDatumBuilder)

    protected def getDatumBuilder(): p.Datum.Builder = p.Datum.newBuilder().setType(datumType)

}

final class NullDatum extends Datum[Null] {

    protected val datumType = R_NULL
    protected val value = null

}

final class BoolDatum(val value: Boolean) extends Datum[Boolean] {

    protected val datumType = R_BOOL

    protected override def getDatumBuilder() = super.getDatumBuilder().setRBool(value)

}

final class NumDatum(val value: Double) extends Datum[Double] {

    protected val datumType = R_NUM

    protected override def getDatumBuilder() = super.getDatumBuilder().setRNum(value)

}

final class StrDatum(val value: String) extends Datum[String] {

    protected val datumType = R_STR

    protected override def getDatumBuilder() = super.getDatumBuilder().setRStr(value)

}

object ArrayDatum {

    def unwrap(datum: Seq[p.Datum]): Any = {
        datum.map { Datum.unwrap(_) }
    }

}

final class ArrayDatum[T](val value: Seq[T]) extends Datum[Seq[T]] {

    protected val datumType = R_ARRAY

}

object ObjectDatum {

    def unwrap(datum: Seq[p.Datum]): Any = ???

}

final class ObjectDatum[T](val value: Seq[T]) extends Datum[Seq[T]] {

    protected val datumType = R_OBJECT

}
