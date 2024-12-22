import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {CoursesService} from "./courses.service";
import {json} from "express";

@Controller('courses')
export class CoursesController {

    constructor(private readonly coursesService: CoursesService) {}

    @Get()
    async getAll(): Promise<string> {
        const courses = await this.coursesService.findAll();
        return JSON.stringify(courses);
    }

    @Get(':id')
    async getById(@Param() params: any):Promise<string>{
        const course = await this.coursesService.findOne(params.id);
        return JSON.stringify(course);
    }

    @Post()
    async create(@Body() body:any):Promise<string>{
        const course = await this.coursesService.create(body);
        return JSON.stringify(course);
    }

    @Put(':id')
    async update(@Param() params: any, @Body() body:any):Promise<string>{
        const course = await this.coursesService.update(params.id, body);
        return JSON.stringify(course);
    }

    @Delete(':id')
    async delete(@Param() params: any):Promise<string>{
        const course = await this.coursesService.delete(params.id);
        return JSON.stringify(course);
    }
}
