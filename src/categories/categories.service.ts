import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Categories} from "./categories";
import {Repository} from "typeorm";

@Injectable()
export class CategoriesService {
    private baseUrl="http://192.168.0.167:5000/uploads/";
    constructor(
        @InjectRepository(Categories)
        private categoriesRepository: Repository<Categories>,
    ) {}

    private mapCategoryImgPath(category: Categories): Categories{
        if(category.categoryImg){
            category.categoryImg = `${this.baseUrl}/${category.categoryImg}`;
        }
        return category;
    }

    async findAll():Promise<Categories[]>{
        let categories = await this.categoriesRepository.find({
            select:["id", "name", "categoryImg"]
        });
        return categories.map(category => this.mapCategoryImgPath(category));
    }

    async create(category: Categories):Promise<Categories>{
        return this.categoriesRepository.save(category);
    }
}
