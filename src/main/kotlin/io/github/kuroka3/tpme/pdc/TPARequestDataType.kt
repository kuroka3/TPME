package io.github.kuroka3.tpme.pdc

import io.github.kuroka3.tpme.classes.TPARequest
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType

class TPARequestDataType : PersistentDataType<String, TPARequest> {
    override fun getPrimitiveType(): Class<String> {
        return String::class.java
    }

    override fun getComplexType(): Class<TPARequest> {
        return TPARequest::class.java
    }

    override fun fromPrimitive(p0: String, p1: PersistentDataAdapterContext): TPARequest {
        return TPARequest.fromString(p0)
    }

    override fun toPrimitive(p0: TPARequest, p1: PersistentDataAdapterContext): String {
        return p0.toString()
    }
}