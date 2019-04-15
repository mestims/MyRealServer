package br.com.server.firebase

import br.com.server.firebase.model.AppUser
import com.google.firebase.database.*
import org.springframework.stereotype.Repository
import org.springframework.web.context.request.async.DeferredResult
import rx.Single

@Repository
class DataRepository {


    fun getUsers(): DeferredResult<List<AppUser>> {
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users/")
        val result = DeferredResult<List<AppUser>>()

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError?) {
                result.setErrorResult(error)
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                val users = snapshot?.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards AppUser>>() {})
                if (users?.isNotEmpty() == true) {
                    result.setResult(users)
                } else {
                    result.setErrorResult("EmptyList")
                }
            }
        })
        return result
    }

    fun getObUsers(): Single<List<AppUser>> {
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users/")
        return Single.create {
            usersRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError?) {

                }

                override fun onDataChange(snapshot: DataSnapshot?) {
                    val users =
                        snapshot?.getValue(object : GenericTypeIndicator<List<@JvmSuppressWildcards AppUser?>>() {})
                    if (users?.isNotEmpty() == true) {
                        it.onSuccess(users.filterNotNull())
                    } else {

                    }
                }
            })
        }
    }

}

//usersRef.setValue(user) { error, _ ->
//    if (error != null) {
//        val responseObj = ResponseEntity
//            .status(401)
//            .body(Response(ApiError(error.message, error.code)))
//
//        response.setResult(responseObj)
//    } else {
//        val responseObj = ResponseEntity.ok(Response(data = "so vamos"))
//        response.setResult(responseObj)
//    }
//}