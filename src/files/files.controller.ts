import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import {FilesService} from "./files.service";

@Controller('files')
export class FilesController {
    constructor(private readonly fileService: FilesService) {}

    @Get()
    async getAll(): Promise<string> {
        const files = await this.fileService.findAll();
        return JSON.stringify(files);
    }

    @Get(':fileId')
    async getById(@Param() params: any): Promise<string> {
        const file = await this.fileService.findOne(params.fileId);
        return JSON.stringify(file);
    }

    @Post()
    async create(@Body() body:any):Promise<string>{
        const file = await this.fileService.create(body);
        return JSON.stringify(file);
    }

    @Put(':fileId')
    async update(@Param() params: any, @Body() body:any):Promise<string>{
        const file = await this.fileService.update(params.fileId, body);
        return JSON.stringify(file);
    }

    @Delete(':fileId')
    async delete(@Param() params: any): Promise<string>{
        const file = await this.fileService.delete(params.fileId);
        return JSON.stringify(file);
    }
}
