package fiix.challenge.fiixexercise.kotlinsample.di

import fiix.challenge.fiixexercise.dp.DataProcessor
import fiix.challenge.fiixexercise.dp.DataSource
import fiix.challenge.fiixexercise.kotlinsample.data.LocalDataSource
import fiix.challenge.fiixexercise.kotlinsample.data.MockRepo
import org.koin.dsl.module


    val fiixAppModule = module {

        single {
            MockRepo()
        }

        single {
            DataProcessor(get())
        }

        single<DataSource>{
            return@single LocalDataSource(get())
        }

    }