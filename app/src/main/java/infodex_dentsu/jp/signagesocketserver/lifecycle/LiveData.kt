package infodex_dentsu.jp.signagesocketserver.lifecycle

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations

fun <X, Y> LiveData<X>.map(func: (X) -> Y) = Transformations.map(this, func)
fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>) = Transformations.switchMap(this, func)

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, block: (T) -> Unit) {
    this.observe(owner, Observer {it ->
        if (it != null) {
            block(it)
        }
    })
}