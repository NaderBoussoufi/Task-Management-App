interface ITask{
    id: string;
    title: string;
    type: string;
    dueDate: Date;
    description: string;
}

interface ITaskTypeOption{
    type: string;
}

interface ITypePercentage{
    type: string;
    count: number;
}
export {ITask,ITaskTypeOption, ITypePercentage}