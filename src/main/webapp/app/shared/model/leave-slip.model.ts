export interface ILeaveSlip {
  id?: number;
  type?: string | null;
  startTime?: string | null;
  endTime?: string | null;
  reason?: string | null;
  file?: string | null;
  superior?: number | null;
  wfstatus?: string | null;
  leavePerson?: string | null;
}

export class LeaveSlip implements ILeaveSlip {
  constructor(
    public id?: number,
    public type?: string | null,
    public startTime?: string | null,
    public endTime?: string | null,
    public reason?: string | null,
    public file?: string | null,
    public superior?: number | null,
    public wfstatus?: string | null,
    public leavePerson?: string | null
  ) {}
}
