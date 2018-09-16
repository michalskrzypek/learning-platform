import {Category} from "./Category";

export class Course {
  title: string;
  description: string;
  category: Category;
  purchases: number;
  tags: string[];
  reviews: any[];
}
