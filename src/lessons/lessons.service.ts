import { Injectable } from '@nestjs/common';
import {Lessons} from "./lessons";
import {InjectRepository} from "@nestjs/typeorm";
import {Files} from "../files/files";
import {Repository} from "typeorm";

@Injectable()
export class LessonsService {
    constructor(
        @InjectRepository(Lessons)
        private lessonsRepository: Repository<Lessons>,
    ) {}

    async findAll():Promise<Lessons[]> {
        return this.lessonsRepository.find();
    }

    async findOne(id: number): Promise<Lessons> {
        return this.lessonsRepository.findOneBy({id:id});
    }

    async create(lesson: Lessons): Promise<Lessons> {
        return this.lessonsRepository.save(lesson);
    }

    async update(id: number, lesson: Lessons): Promise<Lessons> {
        await this.lessonsRepository.update(id, lesson);
        return this.findOne(id);
    }

    async delete(id: number): Promise<void> {
        await this.lessonsRepository.delete(id);
    }
}
