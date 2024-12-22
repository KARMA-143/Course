import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Courses} from "../courses/courses";
import {Repository} from "typeorm";
import {Articles} from "./articles";

@Injectable()
export class ArticlesService {
    constructor(
        @InjectRepository(Articles)
        private articlesRepository: Repository<Articles>,
    ) {}

    async findAll():Promise<Articles[]>{
        return this.articlesRepository.find();
    }

    async findOne(id: number): Promise<Articles> {
        return this.articlesRepository.findOneBy({id:id});
    }

    async create(article: Articles): Promise<Articles> {
        return this.articlesRepository.save(article);
    }

    async update(id: number, article: Articles): Promise<Articles> {
        await this.articlesRepository.update(id, article);
        return this.findOne(id);
    }

    async delete(id: number): Promise<void> {
        await this.articlesRepository.delete(id);
    }
}
