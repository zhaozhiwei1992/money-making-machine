export interface ISysFormulaTab {
  id?: number;
  tabEname?: string | null;
  colEname?: string | null;
  calFormula?: string | null;
  calFormulaDes?: string | null;
  roundType?: string | null;
  weight?: number | null;
  enable?: boolean | null;
}

export class SysFormulaTab implements ISysFormulaTab {
  constructor(
    public id?: number,
    public tabEname?: string | null,
    public colEname?: string | null,
    public calFormula?: string | null,
    public calFormulaDes?: string | null,
    public roundType?: string | null,
    public weight?: number | null,
    public enable?: boolean | null
  ) {
    this.enable = this.enable ?? false;
  }
}
