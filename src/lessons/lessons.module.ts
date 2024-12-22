import { Module } from '@nestjs/common';
import { LessonsService } from './lessons.service';
import { LessonsController } from './lessons.controller';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Lessons} from "./lessons";

@Module({
  imports: [TypeOrmModule.forFeature([Lessons])],
  providers: [LessonsService],
  controllers: [LessonsController]
})
export class LessonsModule {}
