import { Module } from '@nestjs/common';
import { UsersController } from './users.controller';
import { UsersService } from './users.service';
import {TypeOrmModule} from "@nestjs/typeorm";
import {Users} from "./users";
import {JwtModule} from "@nestjs/jwt";
import { PassportModule } from '@nestjs/passport/dist';

@Module({
  imports: [TypeOrmModule.forFeature([Users]),
  PassportModule,
  JwtModule.register({
    secret:"qwerty",
    signOptions:{expiresIn: "2h"}
  })],
  controllers: [UsersController],
  providers: [UsersService]
})
export class UsersModule {}
