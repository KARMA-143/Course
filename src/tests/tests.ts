import { Schema } from 'mongoose';

export const TestSchema = new Schema({
    title: { type: String, required: true },
    courseId: { type: Number, required: true },
    lessonId: { type: Number, required: true },
    questions: [
        {
            questionText: { type: String, required: true },
            options: { type: [String], required: true },
            correctAnswer: { type: String, required: true },
        },
    ],
}, { timestamps: true });
