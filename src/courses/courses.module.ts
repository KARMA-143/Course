import { Module } from '@nestjs/common';
import { CoursesService } from './courses.service';
import { CoursesController } from './courses.controller';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Courses} from "./courses";
import {CategoriesModule} from "../categories/categories.module";

@Module({
  imports: [TypeOrmModule.forFeature([Courses]), CategoriesModule],
  providers: [CoursesService],
  controllers: [CoursesController]
})
export class CoursesModule {}
