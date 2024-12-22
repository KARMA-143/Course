import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {ArticlesService} from "./articles.service";

@Controller('courses/:courseId/articles')
export class ArticlesController {
    constructor(private readonly articlesService: ArticlesService) {}

    @Get()
    async getAll():Promise<string>{
        const articles = await this.articlesService.findAll();
        return JSON.stringify(articles);
    }

    @Get(':articleId')
    async getById(@Param() params: any):Promise<string>{
        const article = await this.articlesService.findOne(params.articleId);
        return JSON.stringify(article);
    }

    @Post()
    async create(@Body() body:any):Promise<string>{
        const article = await this.articlesService.create(body);
        return JSON.stringify(article);
    }

    @Delete(':articleId')
    async delete(@Param() params: any, @Body() body:any):Promise<string>{
        const article = await this.articlesService.delete(params.articleId);
        return JSON.stringify(article);
    }

    @Put(':articleId')
    async update(@Param() params: any, @Body() body:any):Promise<string>{
        const article = await this.articlesService.update(params.articleId, body);
        return JSON.stringify(article);
    }
}
