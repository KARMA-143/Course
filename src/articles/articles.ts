import {Column, Entity, ManyToOne, PrimaryGeneratedColumn} from "typeorm";
import {Courses} from "../courses/courses";

@Entity()
export class Articles {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    title: string;

    @Column('text')
    content: string;

    @ManyToOne(()=>Courses, (courses)=>courses.articles)
    course: Courses;
}
