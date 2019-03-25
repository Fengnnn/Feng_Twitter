package com.example.feng_twitter.Data

enum class ActivityResultCode(val code: Int) {
    API_REQUEST_SUCCESS(11110),
    API_REQUEST_FAILED(1111)

}

enum class ActivityRequestCode(val code: Int) {
    LOGIN_REQUEST(2)
}

enum class ApiRequestResponseCode(val code: Int) {
    HTTP_OK(200),
    HTTP_UNAUTHENTICATED(401)
}

enum class MapKeyValues(val value: String) {
    RESPONSE_CODE("code"),
    RESPONSE_BODY("body")
}