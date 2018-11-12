import {Category} from "./Category";

export class Course {
  id: number;
  title: string;
  description: string;
  category: Category;
  enrollments: number;
  tags: string[];
  reviews: any[];
}
