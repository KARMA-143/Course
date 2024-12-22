import { Module } from '@nestjs/common';
import { ArticlesController } from './articles.controller';
import { ArticlesService } from './articles.service';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Articles} from "./articles";

@Module({
  imports: [TypeOrmModule.forFeature([Articles])],
  controllers: [ArticlesController],
  providers: [ArticlesService]
})
export class ArticlesModule {}
