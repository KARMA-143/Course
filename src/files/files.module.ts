import { Module } from '@nestjs/common';
import { FilesService } from './files.service';
import { FilesController } from './files.controller';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Files} from "./files";

@Module({
  imports: [TypeOrmModule.forFeature([Files])],
  providers: [FilesService],
  controllers: [FilesController]
})
export class FilesModule {}
