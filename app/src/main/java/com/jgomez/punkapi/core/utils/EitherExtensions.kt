package com.jgomez.punkapi.core.utils
import com.jgomez.punkapi.core.utils.Either.Failure
import com.jgomez.punkapi.core.utils.Either.Success
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T, E> Either<T, E>.onSuccess(action: (T) -> Unit): Either<T, E> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (this is Success) action(data)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T1, E1, T2, E2> Either<T1, E1>.map(
    onSuccess: (T1) -> T2,
    onFailure: (E1) -> E2,
): Either<T2, E2> {

    contract {
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
    }

    return when (this) {
        is Success -> Success(onSuccess(data))
        is Failure -> Failure(onFailure(error))
    }
}
