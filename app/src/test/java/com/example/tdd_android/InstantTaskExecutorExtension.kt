package com.example.tdd_android

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantTaskExecutorExtension:BeforeEachCallback,AfterEachCallback {
      private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    @Suppress("FunctionName")
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain( mainThreadSurrogate)
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable)= runnable.run()
            override fun postToMainThread(runnable: Runnable)=runnable.run()
            override fun isMainThread(): Boolean =  true
        })
    }

    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()

        mainThreadSurrogate.close()
    }
}