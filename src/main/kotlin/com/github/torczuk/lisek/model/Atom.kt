package com.github.torczuk.lisek.model

data class Atom(val symbol: String) : Expression {
    override fun toString() = symbol
}