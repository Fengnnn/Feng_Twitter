package com.example.feng_twitter.Controllers.AuthApi.Objects

import com.example.feng_twitter.Controllers.AuthApi.IFakedResponse
import com.example.feng_twitter.Controllers.AuthApi.IFakedResponseBody

object FakedObjects {
    fun getFakedSuccessResponse(): IFakedResponse {
        val fakedResponseBody = FakeResponseBody(200, "fakedToken")
        return FakedResponse(true, fakedResponseBody)

    }

    fun getFakedFailedResponse(): IFakedResponse {
        val fakedResponseBody = FakeResponseBody(401, "Unauthorized")
        return FakedResponse(false, fakedResponseBody)
    }
}

class FakedResponse(override val ok: Boolean, override val responseBody: IFakedResponseBody) :
    IFakedResponse {

}

class FakeResponseBody(override val code: Number, override val body: String) :
    IFakedResponseBody {

}
