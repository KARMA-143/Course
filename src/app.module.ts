import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { CoursesModule } from './courses/courses.module';
import { LessonsModule } from './lessons/lessons.module';
import { ArticlesModule } from './articles/articles.module';
import { TestsModule } from './tests/tests.module';
import { FilesModule } from './files/files.module';
import {TypeOrmModule} from "@nestjs/typeorm";
import {MongooseModule} from '@nestjs/mongoose'
import {JwtModule} from "@nestjs/jwt";
import { CategoriesModule } from './categories/categories.module';


@Module({
  imports: [UsersModule, CoursesModule, LessonsModule, ArticlesModule, TestsModule, FilesModule, TypeOrmModule.forRoot({
    type: 'postgres',
    host: 'localhost',
    port: 5432,
    username: 'postgres',
    password: '1234',
    database: 'educationAppDB',
    autoLoadEntities: true, // Автоматически подключать сущности
    synchronize: true, // Синхронизация схемы (только для разработки)
  }),
    MongooseModule.forRoot('mongodb://localhost:27017/education', {
      retryWrites: true,        // Включить автоматическое повторение записи в случае ошибки
      w: 'majority',
    }),
    JwtModule,
    CategoriesModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
