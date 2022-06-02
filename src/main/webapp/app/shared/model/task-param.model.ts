export interface ITaskParam {
  id?: number;
  name?: string | null;
  cronExpression?: string | null;
  startClass?: string | null;
  enable?: boolean | null;
}

export class TaskParam implements ITaskParam {
  constructor(
    public id?: number,
    public name?: string | null,
    public cronExpression?: string | null,
    public startClass?: string | null,
    public enable?: boolean | null
  ) {
    this.enable = this.enable ?? false;
  }
}
