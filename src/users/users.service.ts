import { Injectable } from '@nestjs/common';
import {InjectRepository} from "@nestjs/typeorm";
import {Repository} from "typeorm";
import {Users} from "./users";
import * as bcrypt from 'bcrypt';
import { JwtService } from '@nestjs/jwt';

@Injectable()
export class UsersService {
    constructor(
        @InjectRepository(Users)
        private usersRepository: Repository<Users>,
        private readonly jwtService: JwtService,
    ) {}

    async register(user: Users){
        const salt =await bcrypt.genSalt();
        user.password=await bcrypt.hash(user.password, salt);
        const newUser = await this.usersRepository.save(user);
        const payload = { username: newUser.name, sub: newUser.id };
        return {
            access_token: this.jwtService.sign(payload),
        };
    }

    async login(email: string, password: string){
        const user = await this.usersRepository.findOne({ where: { email } });
        if (!user) {
            throw new Error('Invalid credentials'); // Ошибка, если пользователь не найден
        }

        // Сравниваем введенный пароль с хешированным паролем из базы данных
        const isPasswordValid = await bcrypt.compare(password, user.password);
        if (!isPasswordValid) {
            throw new Error('Invalid credentials'); // Ошибка, если пароль не совпадает
        }

        // Генерируем JWT токен
        const payload = { username: user.name, sub: user.id };
        return {
            access_token: this.jwtService.sign(payload),
        };
    }
}
