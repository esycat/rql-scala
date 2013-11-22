package com.esyfur.rql.core

import com.esyfur.rql.ast.{Zip}

private[rql] trait Stream extends Selection {

    def zip: Stream = new Zip(this)

}
