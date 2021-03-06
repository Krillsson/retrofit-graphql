package io.github.wax911.library.model.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by max on 2018/03/16.
 * Query & Variable builder for graph requests
 */
@Parcelize
class QueryContainerBuilder(private val queryContainer: QueryContainer = QueryContainer()) : Parcelable {

    fun setQuery(query: String?): QueryContainerBuilder {
        this.queryContainer.query = query
        return this
    }

    fun putVariable(key: String, value: Any?): QueryContainerBuilder {
        queryContainer.putVariable(key, value)
        return this
    }

    fun getVariable(key: String): Any? {
        return when {
            contrainsKey(key) -> queryContainer.variables[key]
            else -> null
        }
    }

    fun contrainsKey(key: String): Boolean {
        return queryContainer.variables.containsKey(key)
    }

    /**
     * Should only be called by the GraphQLConverter or any other subclasses of it
     * after the query has been added to the request
     * @see io.github.wax911.library.converter.GraphConverter
     */
    fun build(): QueryContainer {
        return queryContainer
    }
}