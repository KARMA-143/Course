import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {CoursesService} from "./courses.service";
import {json} from "express";
import {CategoriesService} from "../categories/categories.service";

@Controller('courses')
export class CoursesController {

    constructor(private readonly coursesService: CoursesService, private readonly categoryService: CategoriesService) {}

    private shuffleArray(array) {
        return array.sort(() => Math.random() - 0.5);
    }

    @Get()
    async getAll(): Promise<string> {
        const courses = await this.coursesService.findAll();
        let categories = await this.categoryService.findAll();
        categories=this.shuffleArray(categories).slice(0,4);
        return JSON.stringify({courses, categories});
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
