import { IUser } from 'app/shared/model/user.model';
import { IClassroom } from 'app/shared/model/classroom.model';

export interface IStudent {
  id?: number;
  studentId?: number;
  name?: string | null;
  user?: IUser | null;
  classrooms?: IClassroom[] | null;
}

export const defaultValue: Readonly<IStudent> = {};
