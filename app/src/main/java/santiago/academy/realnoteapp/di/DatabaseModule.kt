package santiago.academy.realnoteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import santiago.academy.realnoteapp.db.DatabaseApp
import santiago.academy.realnoteapp.utilities.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DatabaseApp::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(
        db: DatabaseApp
    ) = db.getNoteDao()

    @Singleton
    @Provides
    fun provideFolderDao(
        db: DatabaseApp
    ) = db.getFolderDao()
}