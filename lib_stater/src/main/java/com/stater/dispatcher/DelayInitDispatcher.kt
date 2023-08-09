package com.stater.dispatcher

import android.os.Looper
import android.os.MessageQueue.IdleHandler
import com.stater.task.DispatchRunnable
import com.stater.task.Task
import java.util.LinkedList
import java.util.Queue

/**
 * 延迟初始化
 * 利用IdleHandler的等待主线程空闲特性，在空闲时才去执行任务
 */
class DelayInitDispatcher {
    private val mDelayTasks: Queue<Task> = LinkedList()
    private val mIdleHandler = IdleHandler {
        if (mDelayTasks.size > 0) {
            val task = mDelayTasks.poll()
            DispatchRunnable(task).run()
        }
        !mDelayTasks.isEmpty()
    }

    fun addTask(task: Task): DelayInitDispatcher {
        mDelayTasks.add(task)
        return this
    }

    fun start() {
        Looper.myQueue().addIdleHandler(mIdleHandler)
    }
}