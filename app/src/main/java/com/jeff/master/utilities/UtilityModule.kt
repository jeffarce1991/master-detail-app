package com.jeff.master.utilities

import com.jeff.master.utilities.rx.RxSchedulerUtilsModule
import dagger.Module


@Module(includes = [RxSchedulerUtilsModule::class])
abstract class UtilityModule {

}