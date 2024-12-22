import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Courses} from "./courses";
import {Repository} from "typeorm";

@Injectable()
export class CoursesService {
    constructor(
        @InjectRepository(Courses)
        private coursesRepository: Repository<Courses>,
    ) {}

    async findAll(): Promise<Courses[]> {
        return this.coursesRepository.find();
    }

    async findOne(id: number): Promise<Courses> {
        return this.coursesRepository.findOneBy({id: id});
    }

    async create(course: Courses): Promise<Courses> {
        return this.coursesRepository.save(course);
    }

    async update(id: number, course: Courses): Promise<Courses> {
        await this.coursesRepository.update(id, course);
        return this.findOne(id);
    }

    async delete(id: number): Promise<void> {
        await this.coursesRepository.delete(id);
    }
}
