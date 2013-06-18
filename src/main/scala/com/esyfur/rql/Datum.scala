package com.esyfur.rql

import com.rethinkdb.{Ql2 => p}

object Datum {

    def deconstruct(datum: p.Datum) = {
        println("Datum type: %s".format(datum.getType))
        import p.Datum.DatumType
        datum.getType match {
            case DatumType.R_NULL => null
            case DatumType.R_BOOL => datum.getRBool
            case DatumType.R_NUM => datum.getRNum
            case DatumType.R_STR => datum.getRStr
            case DatumType.R_ARRAY => datum.getRArrayList
            case DatumType.R_OBJECT => datum.getRObjectList
            case datumType => {
                throw new RqlDriverError("Unexpected datum type %s.".format(datumType))
            }
        }
    }

}

abstract class Datum extends Query {

    var datumType: p.Datum.DatumType = _

}
