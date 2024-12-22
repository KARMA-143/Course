import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import {Files} from "./files";
import {Courses} from "../courses/courses";

@Injectable()
export class FilesService {
    constructor(
        @InjectRepository(Files)
        private articlesRepository: Repository<Files>,
    ) {}

    async findAll(): Promise<Files[]> {
        return this.articlesRepository.find();
    }

    async findOne(id: number): Promise<Files> {
        return this.articlesRepository.findOneBy({id: id});
    }

    async create(Files: Files ): Promise<Files> {
        return this.articlesRepository.save(Files);
    }

    async update(id: number, Files: Files ): Promise<Files> {
        await this.articlesRepository.update(id, Files);
        return this.findOne(id);
    }

    async delete(id: number): Promise<void> {
        await this.articlesRepository.delete(id);
    }
}
