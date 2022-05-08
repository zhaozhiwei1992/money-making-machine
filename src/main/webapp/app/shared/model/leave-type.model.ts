export interface ILeaveType {
  id?: number;
  code?: string | null;
  name?: string | null;
  parentid?: number | null;
  enabled?: boolean | null;
}

export class LeaveType implements ILeaveType {
  constructor(
    public id?: number,
    public code?: string | null,
    public name?: string | null,
    public parentid?: number | null,
    public enabled?: boolean | null
  ) {
    this.enabled = this.enabled ?? false;
  }
}
