import { Module } from '@nestjs/common';
import { CategoriesService } from './categories.service';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Categories} from "./categories";
import { CategoriesController } from './categories.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Categories])],
  providers: [CategoriesService],
  exports: [CategoriesService],
  controllers: [CategoriesController],
})
export class CategoriesModule {}
