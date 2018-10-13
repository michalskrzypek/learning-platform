import {Category} from "./Category";

export class Course {
  id: number;
  title: string;
  description: string;
  category: Category;
  purchases: number;
  tags: string[];
  reviews: any[];
}
