import { IStudent } from 'app/shared/model/student.model';

export interface IClassroom {
  id?: number;
  classroomId?: number;
  className?: string | null;
  students?: IStudent[] | null;
}

export const defaultValue: Readonly<IClassroom> = {};
