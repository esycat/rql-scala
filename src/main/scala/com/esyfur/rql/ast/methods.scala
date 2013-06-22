package com.esyfur.rql.ast

import com.rethinkdb.{Ql2 => p}
import com.esyfur.rql.{Query, BiOpQuery, TopLevelQuery, MethodQuery}

class Var extends Query {

    val termType = p.Term.TermType.VAR

    /*
    def compose(args, optargs) = "var_ " + args(0)
    */
}

class JavaScript extends TopLevelQuery {

    val termType = p.Term.TermType.JAVASCRIPT
    val st = "js"

}

class UserError extends TopLevelQuery {

    val termType = p.Term.TermType.ERROR
    val st = "error"

}

class Default extends Query {

    val termType = p.Term.TermType.DEFAULT
    val st = "default"

}

class ImplicitVar extends Query {

    val termType = p.Term.TermType.IMPLICIT_VAR

    /*
    def compose(args, optargs) = 'r.row'
    */
}

class Append extends MethodQuery {

    val termType = p.Term.TermType.APPEND
    val st = "append"
}

class Prepend extends MethodQuery {

    val termType = p.Term.TermType.PREPEND
    val st = "prepend"
}

class Difference extends MethodQuery {

    val termType = p.Term.TermType.DIFFERENCE
    val st = "difference"
}

class SetInsert extends MethodQuery {

    val termType = p.Term.TermType.SET_INSERT
    val st = "set_insert"
}

class SetUnion extends MethodQuery {

    val termType = p.Term.TermType.SET_UNION
    val st = "set_union"
}

class SetIntersection extends MethodQuery {

    val termType = p.Term.TermType.SET_INTERSECTION
    val st = "set_intersection"
}

class SetDifference extends MethodQuery {

    val termType = p.Term.TermType.SET_DIFFERENCE
    val st = "set_difference"
}

class Slice extends Query {

    val termType = p.Term.TermType.SLICE

    /*
    def compose(args, optargs) = T(args[ 0], '[', args[ 1], ':', args[ 2], ']')
    }
    */
}

class Skip extends MethodQuery {

    val termType = p.Term.TermType.SKIP
    val st = "skip"
}

class Limit extends MethodQuery {

    val termType = p.Term.TermType.LIMIT
    val st = "limit"

}

class GetAttr extends Query {

    val termType = p.Term.TermType.GETATTR

    /*
    def compose(args, optargs) = T(args[ 0], '[', args[ 1], ']')
    */
}

class Contains extends MethodQuery {

    val termType = p.Term.TermType.CONTAINS
    val st = "contains"
}

class HasFields extends MethodQuery {

    val termType = p.Term.TermType.HAS_FIELDS
    val st = "has_fields"

}

class WithFields extends MethodQuery {

    val termType = p.Term.TermType.WITH_FIELDS
    val st = "with_fields"

}

class Keys extends MethodQuery {

    val termType = p.Term.TermType.KEYS
    val st = "keys"

}

class Pluck extends MethodQuery {

    val termType = p.Term.TermType.PLUCK
    val st = "pluck"

}

class Without extends MethodQuery {

    val termType = p.Term.TermType.WITHOUT
    val st = "without"

}

class Merge extends MethodQuery {

    val termType = p.Term.TermType.MERGE
    val st = "merge"

}

class Between extends MethodQuery {

    val termType = p.Term.TermType.BETWEEN
    val st = "between"

}
