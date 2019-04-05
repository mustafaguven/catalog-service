package com.mg.catalog.domain

import com.google.gson.Gson
import com.google.gson.GsonBuilder

abstract class AbstractTree<I, C>(id: I, node: C) {
    private val map = HashMap<I, Node<I, C>>()
    private val root: Node<I, C>
    private val gson: Gson

    init {
        root = Node(id, node)
        map[id] = root
        gson = GsonBuilder().create()
    }

    protected fun addChild(parentId: I, id: I, node: C) {
        val parent = map[parentId]
        val child = Node(id, node)
        parent?.nodes?.add(child)
        map[id] = child
    }

    fun getById(id: I): C {
        return map[id]!!.node
    }

    open fun show(id: I): String {
        return gson.toJson(map[id])
    }

    private class Node<I, A> (private val id: I, val node: A) {
        val nodes = ArrayList<Node<I, A>>()
    }
}