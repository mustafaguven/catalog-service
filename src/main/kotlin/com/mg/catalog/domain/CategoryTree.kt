package com.mg.catalog.domain

import com.google.gson.Gson
import com.google.gson.GsonBuilder


class CategoryTree<I, C>(id: I, category: C) {
    private val map = HashMap<I?, Node<I, C>>()
    private val root: Node<I, C>
    private val gson: Gson

    init {
        root = Node(id, category)
        map[id] = root
        gson = GsonBuilder().create()
    }

    fun addChild(parentId: I, id: I?, category: C) {
        val parent = map[parentId]
        val child = Node(id, category)
        parent?.categories?.add(child)
        map[id] = child
    }

    fun getById(id: I): C {
        return map[id]!!.category
    }

    fun subtreeToString(id: I): String {
        return gson.toJson(map[id])
    }

    private class Node<I, A> (private val id: I?, val category: A) {
        val categories = ArrayList<Node<I, A>>()
    }
}

