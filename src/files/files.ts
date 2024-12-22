import {Column, Entity, ManyToOne, PrimaryGeneratedColumn} from "typeorm";
import {Lessons} from "../lessons/lessons";

@Entity()
export class Files {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    filename: string;

    @Column()
    extension: string;

    @Column()
    url: string;

    @ManyToOne(()=>Lessons, (lesson)=>lesson.files)
    lesson: Lessons;
}
