import {Column, Entity, ManyToOne, OneToMany, PrimaryGeneratedColumn} from "typeorm";
import {Courses} from "../courses/courses";
import {Files} from "../files/files";

@Entity()
export class Lessons {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    title: string;

    @Column({nullable: true})
    content: string;

    @Column()
    order: number;

    @ManyToOne(()=>Courses, (course)=>course.lessons)
    course: Courses;

    @OneToMany(()=>Files,(file)=>file.lesson)
    files: Files[];
}
