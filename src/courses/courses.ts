import {Column, Entity, OneToMany, PrimaryGeneratedColumn} from "typeorm";
import {Lessons} from "../lessons/lessons";
import {Articles} from "../articles/articles";

@Entity()
export class Courses {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    title: string;

    @Column()
    owner: string;

    @Column("float")
    price: number;

    @Column("float")
    star: number;

    @Column()
    courseCover: string;

    @Column({nullable: true})
    description: string;

    @Column()
    createdAt: Date;

    @OneToMany(()=> Lessons, (lesson)=>lesson.course)
    lessons: Lessons[];

    @OneToMany(()=>Articles, (article)=>article.course)
    articles: Articles[];
}
