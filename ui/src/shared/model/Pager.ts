export class Pager {
  number: number = 0;
  size: number = 9;
  totalElements: number;
  totalPages: number;
  first: boolean = true;
  last: boolean = false;
  sort: {
    sorted: boolean,
    unsorted: boolean
  };
  numberOfElements: number;
}
