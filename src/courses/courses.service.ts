import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Courses} from "./courses";
import {Repository} from "typeorm";

@Injectable()
export class CoursesService {
    private baseUrl="http://192.168.0.167:5000/uploads";
    constructor(
        @InjectRepository(Courses)
        private coursesRepository: Repository<Courses>,
    ) {}

    private mapCourseCoverPath(course: Courses): Courses {
        if (course.courseCover) {
            course.courseCover = `${this.baseUrl}/${course.courseCover}`;
        }
        return course;
    }

    async findAll(): Promise<Courses[]> {
        let courses = await this.coursesRepository.find({
            select:["id", "title", "owner", "price", "star","courseCover"]
        });
        return courses.map(course => this.mapCourseCoverPath(course));
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
