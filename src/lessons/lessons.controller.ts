import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {LessonsService} from "./lessons.service";

@Controller('courses/:courseId/lessons')
export class LessonsController {
    constructor(private lessonService: LessonsService) {}

    @Get()
    async getLessons():Promise<string> {
        const lessons=await this.lessonService.findAll();
        return JSON.stringify(lessons);
    }

    @Get(':lessonId')
    async getLesson(@Param() params: any):Promise<string> {
        const lesson = await this.lessonService.findOne(params.lessonId);
        return JSON.stringify(lesson);
    }

    @Post()
    async createLesson(@Body() body:any):Promise<string>{
        const lesson = await this.lessonService.create(body);
        return JSON.stringify(lesson);
    }

    @Put(':lessonId')
    async update(@Param() params: any, @Body() body:any):Promise<string>{
        const lesson = await this.lessonService.update(params.lessonId, body);
        return JSON.stringify(lesson);
    }

    @Delete(':lessonId')
    async delete(@Param() params: any):Promise<string>{
        const lesson = await this.lessonService.delete(params.lessonId);
        return JSON.stringify(lesson);
    }
}
