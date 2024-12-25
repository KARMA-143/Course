import {Body, Controller, Get, Post} from '@nestjs/common';
import {CategoriesService} from "./categories.service";

@Controller('categories')
export class CategoriesController {
    constructor(private readonly categoriesService: CategoriesService) {}

    @Post()
    async create(@Body() body:any):Promise<string>{
        const category = await this.categoriesService.create(body);
        return JSON.stringify(category);
    }

    @Get()
    async findAll():Promise<string>{
        const categories = await this.categoriesService.findAll();
        return JSON.stringify(categories);
    }
}
