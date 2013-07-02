package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Query, TopLevelQuery, MethodQuery}

class Var extends Query {

    protected val termType = p.Term.TermType.VAR

    /*
    def compose(args, optargs) = "var_ " + args(0)
    */
}

class JavaScript extends TopLevelQuery {

    protected val termType = p.Term.TermType.JAVASCRIPT
    val st = "js"

}

class UserError extends TopLevelQuery {

    protected val termType = p.Term.TermType.ERROR
    val st = "error"

}

class Default extends Query {

    protected val termType = p.Term.TermType.DEFAULT
    val st = "default"

}

class ImplicitVar extends Query {

    protected val termType = p.Term.TermType.IMPLICIT_VAR

    /*
    def compose(args, optargs) = 'r.row'
    */
}

class SetInsert extends MethodQuery {

    protected val termType = p.Term.TermType.SET_INSERT
    val st = "set_insert"
}

class SetUnion extends MethodQuery {

    protected val termType = p.Term.TermType.SET_UNION
    val st = "set_union"
}

class SetIntersection extends MethodQuery {

    protected val termType = p.Term.TermType.SET_INTERSECTION
    val st = "set_intersection"
}

class SetDifference extends MethodQuery {

    protected val termType = p.Term.TermType.SET_DIFFERENCE
    val st = "set_difference"
}

class Slice extends Query {

    protected val termType = p.Term.TermType.SLICE

    /*
    def compose(args, optargs) = T(args[ 0], '[', args[ 1], ':', args[ 2], ']')
    }
    */
}

class Skip extends MethodQuery {

    protected val termType = p.Term.TermType.SKIP
    val st = "skip"
}

class Limit extends MethodQuery {

    protected val termType = p.Term.TermType.LIMIT
    val st = "limit"

}

class GetField extends Query {

    protected val termType = p.Term.TermType.GET_FIELD

    /*
    def compose(args, optargs) = T(args[ 0], '[', args[ 1], ']')
    */
}

class Contains extends MethodQuery {

    protected val termType = p.Term.TermType.CONTAINS
    val st = "contains"
}

class HasFields extends MethodQuery {

    protected val termType = p.Term.TermType.HAS_FIELDS
    val st = "has_fields"

}

class WithFields extends MethodQuery {

    protected val termType = p.Term.TermType.WITH_FIELDS
    val st = "with_fields"

}

class Keys extends MethodQuery {

    protected val termType = p.Term.TermType.KEYS
    val st = "keys"

}

class Pluck extends MethodQuery {

    protected val termType = p.Term.TermType.PLUCK
    val st = "pluck"

}

class Without extends MethodQuery {

    protected val termType = p.Term.TermType.WITHOUT
    val st = "without"

}

class Merge extends MethodQuery {

    protected val termType = p.Term.TermType.MERGE
    val st = "merge"

}

class Between extends MethodQuery {

    protected val termType = p.Term.TermType.BETWEEN
    val st = "between"

}
